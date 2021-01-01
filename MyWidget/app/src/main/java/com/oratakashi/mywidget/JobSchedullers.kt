package com.oratakashi.mywidget

import android.app.job.JobParameters
import android.app.job.JobService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.widget.RemoteViews

class JobSchedullers : JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {
        val manager = AppWidgetManager.getInstance(this)
        val view = RemoteViews(packageName, R.layout.randomizer_widget)
        val theWidget = ComponentName(this, RandomizerWidget::class.java)
        val lastUpdate = "Random: " + NumberGenerator.generate(100)
        view.setTextViewText(R.id.appwidget_text, lastUpdate)
        manager.updateAppWidget(theWidget, view)
        jobFinished(p0, true)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return false
    }
}