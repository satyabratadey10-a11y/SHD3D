cmake_minimum_required(VERSION 3.22)
project(mapengine)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_FLAGS "-O3 -ffast-math ${CMAKE_CXX_FLAGS}")

include(FetchContent)

# Provide lightweight bundled stubs for building if actual files aren't imported yet
file(MAKE_DIRECTORY ${CMAKE_SOURCE_DIR}/third_party/glm)
file(MAKE_DIRECTORY ${CMAKE_SOURCE_DIR}/third_party/stb)
file(MAKE_DIRECTORY ${CMAKE_SOURCE_DIR}/third_party/tinygltf)

include_directories(third_party/glm)
include_directories(third_party/stb)
include_directories(third_party/tinygltf)

file(GLOB_RECURSE SRC_FILES 
    core/*.cpp 
    renderer/*.cpp 
    scene/*.cpp 
    assets/*.cpp 
    baking/*.cpp 
    input/*.cpp 
    export/*.cpp
)

add_library(mapengine SHARED ${SRC_FILES})
target_link_libraries(mapengine android log GLESv3 EGL jnigraphics)
