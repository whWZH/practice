package com.wzh.learnrv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/8/21.
 */
class MyAdapter extends RecyclerView.Adapter {
    class ViewHo extends RecyclerView.ViewHolder {
        private View root;
        private TextView title,context;
        public ViewHo(View root) {
            super(root);
           title= (TextView) root.findViewById(R.id.title);
            context= (TextView) root.findViewById(R.id.context);
        }

        public TextView getContext() {
            return context;
        }

        public TextView getTitle() {
            return title;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHo(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_cell,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHo vh = (ViewHo) viewHolder;
        itemDATA iD=data[i];
        vh.getTitle().setText(iD.title);
        vh.getContext().setText(iD.context);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    private itemDATA[] data=new itemDATA[]{new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),
            new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("极客学院","IT教育类"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻"),new itemDATA("新闻","网易新闻")};
}
