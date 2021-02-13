-dontwarn com.google.android.material.**
-keep class com.google.android.material.* { *; }

-dontwarn androidx.**
-keep class androidx.* { *; <fields>; }
-keep interface androidx.* { *; <fields>; }

-keep class kotlin.Metadata { *; }
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod

#Rules untuk Main Activity Favorite
#-keepnames class com.oratakashi.favorite.main.**