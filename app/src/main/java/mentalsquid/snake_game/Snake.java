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

        mBitmapHeadRight = BitmapFactory.decodeResource(R.drawable.head);
        mBitmapHeadLeft = BitmapFactory.decodeResource(R.drawable.head);
        mBitmapHeadUp = BitmapFactory.decodeResource(R.drawable.head);
        mBitmapHeadDown = BitmapFactory.decodeResource(R.drawable.head);
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

        mBitmapBody = BitmapFactory.decodeResource(R.drawable.body);
        mBitmapBody = Bitmap.createScaledBitmap(mBitmapBody, segmentSize, segmentSize, false);

        halfWayPoint = movementRange.x * segmentSize / 2;
    }

    void reset(int h, int w) {
        heading = Heading.RIGHT;
        mSegmentLocations.clear();
        mSegmentLocations.add(new Point(h / 2, w / 2));
    }

}
