###fragment源码分析
   1 fragment 添加到 container 里面是在 FragmentManager 的 moveToState
     里面 ：
       f.mContainer = container;
	    f.mView = f.performCreateView(f.getLayoutInflater(
	            f.mSavedFragmentState), container, f.mSavedFragmentState);
	    if (f.mView != null) {
	        f.mView.setSaveFromParentEnabled(false);
	        if (container != null) {
	            Animator anim = loadAnimator(f, transit, true,
	                    transitionStyle);
	            if (anim != null) {
	                anim.setTarget(f.mView);
	                setHWLayerAnimListenerIfAlpha(f.mView, anim);
	                anim.start();
	            }
	            container.addView(f.mView);
	        }
	        if (f.mHidden) f.mView.setVisibility(View.GONE);
	        f.onViewCreated(f.mView, f.mSavedFragmentState);
	    }
	}