cmake_minimum_required(VERSION 3.23)
project(OOAD_Lab)

set(CMAKE_CXX_STANDARD 23)

set(CMD_DIR include/commands)

include_directories(include/commands)

add_executable(OOAD_Lab main.cpp)
