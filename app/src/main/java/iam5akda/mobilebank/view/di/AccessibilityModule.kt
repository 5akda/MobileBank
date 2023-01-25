package iam5akda.mobilebank.view.di

import android.content.Context
import android.view.accessibility.AccessibilityManager
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import iam5akda.mobilebank.view.accessibility.AccessibilityServiceDetector
import iam5akda.mobilebank.view.accessibility.AccessibilityServiceDetectorImpl

@Module
@InstallIn(SingletonComponent::class)
object AccessibilityModule {

    @Provides
    fun provideAccessibilityManager(
        @ApplicationContext context: Context
    ): AccessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

    @Reusable
    @Provides
    fun provideAccessibilityServiceDetector(
        detector: AccessibilityServiceDetectorImpl
    ): AccessibilityServiceDetector = detector
}