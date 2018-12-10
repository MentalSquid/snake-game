package mentalsquid.snake_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;

import java.util.ArrayList;

public class Snake {
    private ArrayList<Point> mSegmentLocations;
    private int mSegmentSize;
    private Point mMoveRange;
    //Device pixel position
    private int halfWayPoint;

    private enum Heading {
        UP, DOWN, RIGHT, LEFT
    }

    private Heading heading = Heading.RIGHT;
    private Bitmap mBitmapHeadRight;
    private Bitmap mBitmapHeadLeft;
    private Bitmap mBitmapHeadUp;
    private Bitmap mBitmapHeadDown;
    private Bitmap mBitmapBody;

    //Snake constructor.
    public Snake(Context context, Point movementRange, int segmentSize) {
        mSegmentLocations = new ArrayList<Point>();
        mSegmentSize = segmentSize;
        mMoveRange = movementRange;

        mBitmapHeadRight = BitmapFactory.decodeResource(context.getResources(), R.drawable.head);
        mBitmapHeadLeft = BitmapFactory.decodeResource(context.getResources(), R.drawable.head);
        mBitmapHeadUp = BitmapFactory.decodeResource(context.getResources(), R.drawable.head);
        mBitmapHeadDown = BitmapFactory.decodeResource(context.getResources(), R.drawable.head);
        //Orient bitmaps
        mBitmapHeadRight = Bitmap.createScaledBitmap(mBitmapHeadRight, segmentSize, segmentSize, false);

        Matrix matrix = new Matrix();
        //Head LEFT
        matrix.preScale(-1, 1);
        mBitmapHeadLeft = Bitmap.createBitmap(mBitmapHeadRight, 0, 0, segmentSize, segmentSize, matrix, true);
        //Head UP
        matrix.preRotate(-90);
        mBitmapHeadUp = Bitmap.createBitmap(mBitmapHeadRight, 0, 0, segmentSize, segmentSize, matrix, true);
        //Head DOWN
        matrix.preRotate(180);
        mBitmapHeadDown = Bitmap.createBitmap(mBitmapHeadRight, 0, 0, segmentSize, segmentSize, matrix, true);

        mBitmapBody = BitmapFactory.decodeResource(context.getResources(), R.drawable.body);
        mBitmapBody = Bitmap.createScaledBitmap(mBitmapBody, segmentSize, segmentSize, false);

        halfWayPoint = movementRange.x * segmentSize / 2;
    }

    void reset(int h, int w) {
        heading = Heading.RIGHT;
        mSegmentLocations.clear();
        mSegmentLocations.add(new Point(h / 2, w / 2));
    }

    void move() {

        //start at back, get segment and move it forward.
        for (int i = mSegmentLocations.size() - 1; i > 0; i--) {
            mSegmentLocations.get(i).x = mSegmentLocations.get(i - 1).x;
            mSegmentLocations.get(i).y = mSegmentLocations.get(i - 1).y;
        }

        Point p = mSegmentLocations.get(0);

        switch (heading) {
            case UP:
                p.y--;
                break;
            case DOWN:
                p.y++;
                break;
            case LEFT:
                p.x--;
                break;
            case RIGHT:
                p.x++;
                break;
        }
        mSegmentLocations.set(0, p);
    }

    boolean detectDeath() {
        boolean isDead = false;

        //wall collision
        if (mSegmentLocations.get(0).x == -1 || mSegmentLocations.get(0).x > mMoveRange.x || mSegmentLocations.get(0).y == -1 || mSegmentLocations.get(0).y > mMoveRange.y) {
            isDead = true;
        }

        //self collision
        for (int i = mSegmentLocations.size() - 1; i > 0; i--) {
            if (mSegmentLocations.get(0).x == mSegmentLocations.get(i).x && mSegmentLocations.get(0).y == mSegmentLocations.get(i).y) {
                isDead = true;
            }
        }
        return isDead;
    }

}
