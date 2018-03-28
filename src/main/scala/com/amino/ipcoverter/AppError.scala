package com.amino.ipcoverter

case class AppError(message: String, throwable: Option[Throwable] = None)
