package tool.xpy.opengl_test.work.ui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by dell on 9/20/2016.
 */
public class MyGLSurfaceView extends GLSurfaceView implements SensorEventListener{
    public MyRender mrender;
    private float ini_angle_Y,ini_angle_X,ini_angle_Z;
    public static float angle=0;
    private int flag;
    public SensorManager msensorManager;
    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context,attrs);
        // TODO Auto-generated constructor stub
        msensorManager=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        mrender=null;
        ini_angle_Y=-90f;
        ini_angle_X=0f;
        ini_angle_Z=0f;
        flag=0;
        enablesensor();
    }


    public void setMrender(MyRender r){
        mrender=r;
        setRenderer(mrender);
    }

      public void enablesensor() {
        msensorManager.registerListener(this, msensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
          msensorManager.registerListener(this, msensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);

    };
    public void disablesensor()
    {
        msensorManager.unregisterListener(this);
    }


    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent arg0) {
        // TODO Auto-generated method stub
        if(arg0.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            float[] values = arg0.values;

            angle = values[2];

        }

        if(arg0.sensor.getType()==Sensor.TYPE_ORIENTATION) {
            float[] values = arg0.values;
            float angle_X = values[1];
            float angle_Y = values[2];


            if(flag==0)
            {
                ini_angle_X=angle_X;
                ini_angle_Y=angle_Y-(angle*1.2f);

                flag=1;
            }
            else if(flag==1){

                mrender.viewX = ( angle_X-ini_angle_X) *6.0f;
                mrender.viewZ = ( angle_Y-ini_angle_Y) *4.0f;

            }
        }

    }
}
