/*
 * codectypedef.h
 *
 *  Created on: 2016-10-5
 *      Author: yangzhihui
 */

#ifndef CODECTYPEDEF_H_
#define CODECTYPEDEF_H_
#include <stddef.h>

typedef enum _video_codec_type
{
    CODEC_TYPE_ENCODER_NONE = -1,
    CODEC_TYPE_ENCODER_H264 = 100,
    CODEC_TYPE_ENCODER_H265,

    CODEC_TYPE_DECODER_H264 = 200,
    CODEC_TYPE_DECODER_H265
} VIDEO_CODEC_TYPE;

#endif /* CODECTYPEDEF_H_ */
