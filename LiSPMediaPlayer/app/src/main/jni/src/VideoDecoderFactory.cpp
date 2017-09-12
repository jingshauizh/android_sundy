/*
 * VideoDecoderFactory.cpp
 *
 *  Created on: 2016-10-5
 *      Author: yangzhihui
 */

#include <string.h>
#ifdef DEBUG_MEM
#include "include/mymalloc.h"
#else
#include <malloc.h>
#endif
#include "include/VideoDecoderFactory.h"

Video_Decoder_ptr getVideoDecoderInstance(VIDEO_CODEC_TYPE decoderType)
{
    Video_Decoder_ptr retDecoder = NULL;
    switch (decoderType)
    {
        case CODEC_TYPE_DECODER_H265:
            break;
        case CODEC_TYPE_DECODER_H264:
            default:
            retDecoder = createVideoDecoderH264();
            break;
    }
    return retDecoder;
}

void destroyVideoDecoderInstance(Video_Decoder_ptr * ppDecoder)
{
    destoryVideoDecoder(ppDecoder);
}
