package com.leavingston.mapexample.exceptions

import java.io.IOException
import java.lang.Exception

class ApiExceptions(message: String): Exception(message)

class NoInternetException(message: String): IOException(message)