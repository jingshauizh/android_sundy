/*
 * util.h
 *
 *  Created on: Oct 14, 2016
 *      Author: elantia
 */

#ifndef INCLUDE_UTIL_H_
#define INCLUDE_UTIL_H_

#define container_of(ptr, type, member) \
    ((type *)(void *)((char *)(ptr) - offsetof(type, member)))

#endif /* INCLUDE_UTIL_H_ */
