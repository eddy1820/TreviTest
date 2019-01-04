package com.tw4gamers.trevitest.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun Disposable.addTo(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)