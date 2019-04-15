package com.huyunit.sample.compass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.huyunit.sample.R
import kotlinx.android.synthetic.main.f_compass.*


/**
 * author: bobo
 * create time: 2017/11/6 上午10:44
 * email: jqbo84@163.com
 */
class CompassFragment : Fragment(), SensorEventListener {

    private var currentDegree = 0f

    companion object {
        fun getInstance(): CompassFragment {

            return CompassFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.f_compass, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 传感器管理器
        val sm = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // 注册传感器(Sensor.TYPE_ORIENTATION(方向传感器);SENSOR_DELAY_FASTEST(0毫秒延迟);
        // SENSOR_DELAY_GAME(20,000毫秒延迟)、SENSOR_DELAY_UI(60,000毫秒延迟))
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_FASTEST)

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    //传感器报告新的值(方向改变)
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type === Sensor.TYPE_ORIENTATION) {
            val degree = event.values[0]

            /*
            RotateAnimation类：旋转变化动画类

            参数说明:

            fromDegrees：旋转的开始角度。
            toDegrees：旋转的结束角度。
            pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
            pivotXValue：X坐标的伸缩值。
            pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
            pivotYValue：Y坐标的伸缩值
            */
            val ra = RotateAnimation(currentDegree, -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f)
            //旋转过程持续时间
            ra.duration = 200
            //罗盘图片使用旋转动画
            mCompassImageView.startAnimation(ra)

            currentDegree = -degree
        }
    }

}