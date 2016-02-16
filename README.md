# Guillotine-menu
铡刀式菜单，实现Yalantis的GuillotineMenu的效果

###SCREENSHOT
![](https://github.com/vienan/Guillotine-menu/blob/master/screenshot/screenshot.gif)

```java
	
	//关闭动画
	private void startCloseAnim() {

        view.setPivotX(calculatePivotX(contentHamburger));
        view.setPivotY(calculatePivotY(contentHamburger));
        toolbar.setPivotX(calculatePivotX(contentHamburger));
        toolbar.setPivotY(calculatePivotY(contentHamburger));

        ObjectAnimator closeAnim = ObjectAnimator.ofFloat(view, "rotation", 0.0f, -90.0f)
                .setDuration((long) (625 * GuillotineInterpolator.ROTATION_TIME));
        ObjectAnimator closeAnim1 = ObjectAnimator.ofFloat(toolbar, "rotation", 70f, 0f)
                .setDuration(625);
        closeAnim1.setInterpolator(new GuillotineInterpolator());
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(closeAnim, closeAnim1);
        closeAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isClosing = true;
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isClosing = false;
                view.setVisibility(View.GONE);
            }

        });
        animSet.start();
    }
    
    
```

```java
	
	//打开动画
	private void startOpenAnim() {

        ObjectAnimator openAnim = ObjectAnimator.ofFloat(view, "rotation", -90f, 0.0f)
                .setDuration(625);
        openAnim.setInterpolator(new GuillotineInterpolator());

        openAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isClosing=isOpening= true;
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isClosing =isOpening= false;
            }
        });
        openAnim.start();


    }
```

###欢迎star和fork👍
