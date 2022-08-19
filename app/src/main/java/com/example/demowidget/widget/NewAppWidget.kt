package com.example.demowidget.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import com.example.demowidget.MainActivity
import com.example.demowidget.R


/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)

        if (appWidgetManager != null) {
            updateAppWidget(context!!,appWidgetManager,appWidgetId)
        }
    }


}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

    val options =appWidgetManager.getAppWidgetOptions(appWidgetId)

    val minWidth = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)
    val maxWidth = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH)


    var imagen:Int
    var diseno:Int
    val imgMediana= R.drawable.img_widget1
    val imgGrande= R.drawable.img_widget

    val layoutMediana=R.layout.widget1
    val layoutGrande=R.layout.new_app_widget

    if(minWidth < 140){
        imagen=imgMediana
        diseno=layoutMediana
    }else{
        imagen=imgGrande
        diseno=layoutGrande
    }


    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    //val views = RemoteViews(context.packageName, R.layout.new_app_widget)
    //views.setTextViewText(R.id.appwidget_text, widgetText)

    //Agregando la accion para ingresar desde el widget
    val inten = Intent(context,MainActivity::class.java)
    val pendingIntent=PendingIntent.getActivity(context,0,inten,0)

    val views = RemoteViews(context.packageName, diseno)
    views.setImageViewResource(R.id.widgetImagenViewn,imagen)
    views.setOnClickPendingIntent(R.id.widgetImagenViewn,pendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}