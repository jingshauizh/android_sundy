-keep class com.raizlabs.android.** { *; }
-keep class io.reactivex.** { *; }
-keep class com.fasterxml.jackson.** { *; }

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keep class com.raizlabs.android.dbflow.config.NewPlayerDBNewPlayerDB_Database