cmake_minimum_required(VERSION 3.4.1)
add_library(luajava SHARED IMPORTED)
set_target_properties(luajava PROPERTIES IMPORTED_LOCATION ../../../../libs/${ANDROID_ABI}/libluajava.so)