# include 
cmake_minimum_required(VERSION 3.12)

# notes that ordering in this case
# matters, cppzmq depends on libzmq
# so libzmq comes first
add_subdirectory(./libzmq)
add_subdirectory(./cppzmq)
add_subdirectory(./linenoise)
