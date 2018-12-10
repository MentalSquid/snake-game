package mentalsquid.snake_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class Apple {

    private Point mLocation = new Point();
    private Point mSpawnRange;
    private int mSize;
    private Bitmap mBitmapApple;

    public Apple(Context context, Point sr, int s) {
        mSpawnRange = sr;
        mSize = s;
        //place the apple off screen until the game starts.
        mLocation.x = -10;

        mBitmapApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, s, s, false);
    }

    void spawn() {
        Random random = new Random();
        mLocation.x = random.nextInt(mSpawnRange.x) + 1;
        mLocation.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    Point getLocation() {
        return mLocation;
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmapApple, mLocation.x * mSize, mLocation.y * mSize, paint);
    }
}
