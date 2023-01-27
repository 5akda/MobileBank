package iam5akda.mobilebank.accessibility

import android.content.Context
import android.view.accessibility.AccessibilityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object AccessibilityModule {

    @Provides
    fun provideAccessibilityManager(
        @ApplicationContext context: Context
    ): AccessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

    @Provides
    fun provideAccessibilityServiceDetector(
        detector: AccessibilityServiceDetectorImpl
    ): AccessibilityServiceDetector = detector
}