package com.example.puzzle8;



import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class PuzzleBoardView extends View {
    public static final int NUM_SHUFFLE_STEPS = 40;
    private Activity activity;
    private PuzzleBoard puzzleBoard;
    private ArrayList<PuzzleBoard> animation;
    private Random random = new Random();

    private Comparator<PuzzleBoard> comparator = new Comparator<PuzzleBoard>() {
        @Override
        public int compare(PuzzleBoard left, PuzzleBoard right) {
            return left.priority() - right.priority();//prioritize the USING MANAHATAON DISTANCE
        }
    };

    public PuzzleBoardView(Context context) {
        super(context);
        activity = (Activity) context;
        animation = null;
    }

    public void initialize(Bitmap imageBitmap, View parent) {
        int width = getWidth();
        puzzleBoard = new PuzzleBoard(imageBitmap, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (puzzleBoard != null) {
            if (animation != null && animation.size() > 0) {
                puzzleBoard = animation.remove(0);
                puzzleBoard.draw(canvas);
                if (animation.size() == 0) {
                    animation = null;
                    puzzleBoard.reset();
                    Toast toast = Toast.makeText(activity, "Solved! ", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    this.postInvalidateDelayed(500);
                }
            } else {
                puzzleBoard.draw(canvas);
            }
        }
    }

    public void shuffle() {
        if (animation == null && puzzleBoard != null) {
            // Do something.
            for(int i = 0;i<7;i++){
                ArrayList<PuzzleBoard> neighbours = puzzleBoard.neighbours();
                int randomInt = random.nextInt(neighbours.size());
                puzzleBoard = neighbours.get(randomInt);
            }
            invalidate();

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (animation == null && puzzleBoard != null) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (puzzleBoard.click(event.getX(), event.getY())) {
                        invalidate();
                        if (puzzleBoard.resolved()) {
                            Toast toast = Toast.makeText(activity, "Congratulations!", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        return true;
                    }
            }
        }
        return super.onTouchEvent(event);
    }

    public void solve() {
        PriorityQueue<PuzzleBoard> que = new PriorityQueue<PuzzleBoard>(1,comparator);
        PuzzleBoard current = new PuzzleBoard(puzzleBoard,-1);
        current.setPreviousBoard(null);
        que.add(current);//ADD SUFFLED BOARD
        while (!que.isEmpty()){
            PuzzleBoard goodState = que.poll();//REMOVING TOP ELEMENT FRO QUEUE
            Log.d("custom","getting top state");

            if(goodState.resolved()){
                ArrayList<PuzzleBoard> steps = new ArrayList();
                while (goodState.getPreviousBoard() != null){
                    steps.add(goodState);
                    goodState = goodState.getPreviousBoard();
                }
                Collections.reverse(steps);
                animation = steps;
                invalidate();
                Log.d("custom", "updating UI");
                break;
            }
            else {
                que.addAll(goodState.neighbours());
            }
        }
    }
}
