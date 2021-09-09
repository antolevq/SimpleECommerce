package com.leva.extentions

import android.content.Context
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog

/**
 * Extension Function that return a drawable from a file name
 * @param fileName a relative path of local image
 * @return         the related drawable
 * @see            Drawable
 */
fun Context.getDrawableImageFromFileName(fileName: String): Int {

    return resources.getIdentifier(fileName, "drawable", packageName)
}

/**
 * Extention function that show a numberPicker in dialog
 * @param minValue      an Int that represent the numberpicker minValue
 * @param startValue    an Int that show the numberPicker start value
 * @param maxvalue      an Int that represent the numberpicker maxValue
 * @see                 NumberPicker
 * @see                 AlertDialog
 */
fun Context.showNumberPickerDialog(
    minValue: Int = 1,
    startValue: Int = 1,
    maxvalue: Int = 100,
    onValueSelected: (value: Int) -> Unit
) {
    val numberPicker = NumberPicker(this)
    numberPicker.minValue = minValue
    numberPicker.maxValue = maxvalue
    numberPicker.value = startValue
    numberPicker.wrapSelectorWheel = false

    AlertDialog.Builder(this)
        .setView(numberPicker)
        .setTitle(getString(R.string.quantity_select))
        .setPositiveButton(
            getString(R.string.add)
        ) { dialog, _ ->
            onValueSelected.invoke(numberPicker.value)
            dialog.dismiss()
        }
        .setNegativeButton(
            getString(R.string.cancel)
        ) { dialog, _ ->
            dialog.dismiss()
        }
        .create().show()

}