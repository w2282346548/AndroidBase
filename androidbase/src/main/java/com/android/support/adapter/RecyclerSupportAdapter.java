package com.android.support.adapter;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.android.support.adapter.animation.AlphaInAnimation;
import com.android.support.adapter.animation.BaseAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stateless on 2017/3/23.
 */

public abstract class RecyclerSupportAdapter<T>extends RecyclerView.Adapter<BaseViewHolder>
        implements IViewBindData<T,BaseViewHolder>,IHeaderFooter,ILayoutManager,IAnimation{


    protected List<T> mData;
    private  Context mContext;

    IMulItemViewType<T> mMulItemViewType;

//    private OnItemClickListener mOnItemClickListener;
//    private OnItemLongClickListener mOnItemLongClickListener;

    RecyclerView mRecyclerView;

    private final int TYPE_HEADER = -0x100;
    private final int TYPE_FOOTER = -0x101;
    View mHeader;
    View mFooter;

    private Interpolator mInterpolator = new LinearInterpolator();
    private long mDuration = 300;
    private boolean mLoadAnimationEnabled;
    private boolean mOnlyOnce = true;
    private BaseAnimation mLoadAnimation;
    private int mLastPosition = -1;


    public RecyclerSupportAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<T>();
        this.mMulItemViewType=null;
    }



    /**
     * Constructor for single item view type.
     * @param context     Context.
     * @param list        Data list.
     */
    public RecyclerSupportAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mData = list==null?new ArrayList<T>():list;
        this.mMulItemViewType=null;
    }

    /**
     * Constructor for multiple item view type.
     *
     * @param context         Context.
     * @param list            Data list.
     * @param mulItemViewType If null, plz override {@link #offerMultiItemViewType()}.
     */
    public RecyclerSupportAdapter(Context context, List<T> list, IMulItemViewType<T> mulItemViewType) {
        this.mContext = context;
        this.mData = list == null ? new ArrayList<T>() : list;
        this.mMulItemViewType = mulItemViewType == null ? offerMultiItemViewType() : mulItemViewType;
    }

    public Context getContext() {
        return mContext;
    }


    public List<T> getData() {
        return mData;
    }


    public void setData(List<T> mData) {
        this.mData = mData;
    }

//    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
//        this.mOnItemClickListener = mOnItemClickListener;
//    }
//
//
//    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
//        this.mOnItemLongClickListener = mOnItemLongClickListener;
//    }

    /**
     * @return Offered an {@link IMulItemViewType} by overriding this method.
     */
    protected IMulItemViewType<T> offerMultiItemViewType() {
        return null;
    }


    @Override
    public int getItemCount() {
        int size = mData == null ? 0 : mData.size();
        if (hasHeaderView())
            size++;
        if (hasFooterView())
            size++;
        return size;
    }


    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (isHeaderView(position)){
            viewType=TYPE_HEADER;
        }else if (isFooterView(position)){
            viewType=TYPE_FOOTER;
        }else {

            if (mMulItemViewType!=null){
                if (hasHeaderView()){
                    position--;
                }
                return mMulItemViewType.getItemViewType(position,mData.get(position));
            }
            return 0;
        }
        return viewType;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        final BaseViewHolder holder;
        if (viewType == TYPE_HEADER && hasHeaderView()) {
            return new BaseViewHolder(getHeaderView());
        } else if (viewType == TYPE_FOOTER && hasFooterView()) {
            return new BaseViewHolder(getFooterView());
        } else {
//            holder = onCreate(null, parent, viewType);
            holder=new BaseViewHolder(createItemView(mContext,viewType));
        }

//        if (!(holder.itemView instanceof AdapterView) && !(holder.itemView instanceof RecyclerView)) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mOnItemClickListener != null) {
//                        mOnItemClickListener.onItemClick(v, viewType, holder.getAdapterPosition());
//                    }
//                }
//            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    if (mOnItemLongClickListener != null) {
//                        mOnItemLongClickListener.onItemLongClick(v, viewType, holder.getAdapterPosition());
//                        return true;
//                    }
//                    return false;
//                }
//            });
//        }

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType != TYPE_HEADER && viewType != TYPE_FOOTER) {
//            onBind(holder, viewType, position, mData.get(hasHeaderView() ? --position : position));
            onBindView(holder.itemView,position,mData.get(hasHeaderView() ? --position : position));
            addLoadAnimation(holder); // Load animation
        }
    }

    /**
     * ------------------------------------ Header / Footer ------------------------------------
     */

    final String TAG = "BaseAdapter";
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (mRecyclerView != null && mRecyclerView != recyclerView)
            Log.i(TAG, "Does not support multiple RecyclerViews now.");
        mRecyclerView = recyclerView;
        // Ensure a situation that add header or footer before setAdapter().
        ifGridLayoutManager();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = null;
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        if (isHeaderView(holder.getLayoutPosition()) || isFooterView(holder.getLayoutPosition())) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
            }
        }
    }

    @Override
    public boolean hasLayoutManager() {
        return mRecyclerView != null && mRecyclerView.getLayoutManager() != null;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return hasLayoutManager() ? mRecyclerView.getLayoutManager() : null;
    }

    @Override
    public View getHeaderView() {
        return mHeader;
    }

    @Override
    public View getFooterView() {
        return mFooter;
    }

    @Override
    public void addHeaderView(View header) {
        if (hasHeaderView())
            throw new IllegalStateException("You have already added a header view.");
        mHeader = header;
        setLayoutParams(mHeader);
        ifGridLayoutManager();
        notifyItemInserted(0);
    }

    @Override
    public void addFooterView(View footer) {
        if (hasFooterView())
            throw new IllegalStateException("You have already added a footer view.");
        mFooter = footer;
        setLayoutParams(mFooter);
        ifGridLayoutManager();
        notifyItemInserted(getItemCount() - 1);
    }

    private void setLayoutParams(View view) {
        if (hasHeaderView() || hasFooterView()) {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                view.setLayoutParams(new StaggeredGridLayoutManager.LayoutParams(
                        StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT,
                        StaggeredGridLayoutManager.LayoutParams.WRAP_CONTENT));
            } else if (layoutManager instanceof GridLayoutManager) {
                view.setLayoutParams(new GridLayoutManager.LayoutParams(
                        GridLayoutManager.LayoutParams.MATCH_PARENT,
                        GridLayoutManager.LayoutParams.WRAP_CONTENT));
            } else {
                view.setLayoutParams(new RecyclerView.LayoutParams(
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT));
            }
        }
    }

    @Override
    public boolean removeHeaderView() {
        if (hasHeaderView()) {
            mHeader = null;
            notifyItemRemoved(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFooterView() {
        if (hasFooterView()) {
            int footerPosition = getItemCount() - 1;
            mFooter = null;
            notifyItemRemoved(footerPosition);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasHeaderView() {
        return getHeaderView() != null;
    }

    @Override
    public boolean hasFooterView() {
        return getFooterView() != null;
    }

    @Override
    public boolean isHeaderView(int position) {
        return hasHeaderView() && position == 0;
    }

    @Override
    public boolean isFooterView(int position) {
        return hasFooterView() && position == getItemCount() - 1;
    }

    private void ifGridLayoutManager() {
        if (hasHeaderView() || hasFooterView()) {
            final RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                        ((GridLayoutManager) layoutManager).getSpanSizeLookup();
                ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (isHeaderView(position) || isFooterView(position)) ?
                                ((GridLayoutManager) layoutManager).getSpanCount() :
                                originalSpanSizeLookup.getSpanSize(position);
                    }
                });
            }
        }
    }

    /**
     * ------------------------------------ Load animation ------------------------------------
     */

    @Override
    public void enableLoadAnimation() {
        enableLoadAnimation(mDuration, new AlphaInAnimation());
    }

    @Override
    public void enableLoadAnimation(long duration, BaseAnimation animation) {
        if (duration > 0) {
            mDuration = duration;
        } else {
            Log.w(TAG, "Invalid animation duration");
        }
        mLoadAnimationEnabled = true;
        mLoadAnimation = animation;
    }

    @Override
    public void cancelLoadAnimation() {
        mLoadAnimationEnabled = false;
        mLoadAnimation = null;
    }

    @Override
    public void setOnlyOnce(boolean onlyOnce) {
        mOnlyOnce = onlyOnce;
    }

    @Override
    public void addLoadAnimation(RecyclerView.ViewHolder holder) {
        if (mLoadAnimationEnabled && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (!mOnlyOnce || holder.getLayoutPosition() > mLastPosition) {
                BaseAnimation animation = mLoadAnimation == null ? new AlphaInAnimation() : mLoadAnimation;
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    anim.setInterpolator(mInterpolator);
                    anim.setDuration(mDuration).start();
                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }


    //---新的加载方式---

    protected abstract View createItemView(Context context,int viewType);

    protected abstract void onBindView(View itemView,int position,T itemData);


}
