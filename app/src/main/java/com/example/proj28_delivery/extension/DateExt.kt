package com.example.proj28_delivery.extension

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("MM.dd", Locale.KOREA)

fun Date.toReadableDateString(): String = dateFormat.format(this)