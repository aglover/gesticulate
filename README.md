# Gesticulate - Android swipe detection made simple

This is a simple utility that makes it easy to detect if a user is swiping a finger across a device's screen (i.e. a fling!). You can detect
up, down, left, and right movements. Furthermore, you can set a distance threshold and a threshold velocity. There are reasonable default
values for both thresholds as well.

## Details

Gesticulate handles swipe calculations for you, but you still need to implement a listener to behave in a meaningful way. For example, you can use Android's `GestureDetector` type and provide it an implementation of `SimpleOnGestureListener` (which is also an Android class and it provides an `onFling` method to implement) that makes use of Gesticulate. 

For example, if you want to handle a swipe down to quit an activity, you can provide a `GestureDetector` like so:

	new GestureDetector(new SimpleOnGestureListener() {
	  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	    try {
	      final SwipeDetector detector = new SwipeDetector(e1, e2, velocityX, velocityY);
		    if (detector.isDownSwipe()) {
			  finish();
			} else {
			  return false;
			}
		} catch (Exception e) {
			// nothing
		}
		return false;
	  }
	});
	
As you can see in the code above, a `SwipeDetector` instance is created with `onFling`'s parameters; once that's done, you can easily detect up, down, left, and right swipes. 

The following methods are available to you:

* `isDownSwipe`
* `isUpSwipe`
* `isLeftSwipe`
* `isRightSwipe`

That's it. Simple, easy, and quite useful.

## Putting it all together

To do something meaningful with a swipe, you have to first _detect_ it; and to detect it, you'll need to attach a few listeners. For example, if you want to listen and handle swipes in a `View` you can do something like this:

	GestureDetector gestureDetector new GestureDetector(new SimpleOnGestureListener() {
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				final SwipeDetector detector = new SwipeDetector(e1, e2, velocityX, velocityY);
				if (detector.isDownSwipe()) {
					finish();
				} else {
					return false;
				}
			} catch (Exception e) {}
			return false;
		}
	});

Now that you've got a `GestureDetector` instance, you can attach it to a `View` like so:

	final View view = findViewById(R.id.widget33);

	view.setOnClickListener(new OnClickListener() {
		public void onClick(View arg0) {}
	});

	view.setOnTouchListener(new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			return gestureDetector.onTouchEvent(event);
		}
	});

Note, you'll need to provide a do-nothing `OnClickListener` to make things work, however, the real meat is in the `OnTouchListener` that ultimately makes use of the `SwipeDetector` inside the `GestureDetector`.

## Who's using it?

Gesticulate is used by [Savvy Words](https://play.google.com/store/apps/details?id=com.b50.savvywords), which you can find in [Google Play](https://play.google.com/store/apps/details?id=com.b50.savvywords) and [Amazon's Appstore for Android](http://www.amazon.com/Beacon50-Savvy-Words/dp/B00C535D20/ref=sr_1_1?s=mobile-apps&ie=UTF8&qid=1365339189&sr=1-1). The Savvy Words Android app uses swipes to advance through word flash cards. It's free so download it and see Gesticulate in action (and enhance your vocabulary)!

### Further details

You can alter the details of how _fast_ a swipe occurs as well as the _length_ of the swipe. Simply provide these values as the last two parameters to a `SwipeDetector` instantiation. 

	public SwipeDetector(MotionEvent e1, MotionEvent e2, float velX, float velY, int minDistance, int minVelocity) {
	    //....
	}

### Even further details

`SwipeDetector` is simple. If you want more details on its usage, then see the `test` directory. You can run the tests via `ant test` or you might have already noticed them run when you jar'ed it!

## Installation

Clone the repo, change directories into the project's root and type `ant`. That'll build a .jar file you can include in your Android `libs` directory. 

## Helpful resources

* [The Disco Blog](http://thediscoblog.com/)
* [Mobile for the masses: A gentle introduction to Android](http://www.ibm.com/developerworks/library/j-mobileforthemasses1/)

# License

The MIT License

Copyright (c) 2013 Andrew Glover

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE