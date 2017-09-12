/*
 * VideoDecoderFactory.h
 *
 *  Created on: 2016-10-5
 *      Author: yangzhihui
 */

#ifndef VIDEODECODERFACTORY_H_
#define VIDEODECODERFACTORY_H_

#include <include/VideoDecoder.h>

#ifdef __cplusplus
extern "C"
{
#endif
    
    Video_Decoder_ptr getVideoDecoderInstance(VIDEO_CODEC_TYPE decoderType);

    void destroyVideoDecoderInstance(Video_Decoder_ptr * ppDecoder);

#ifdef __cplusplus
}
#endif
#endif /* VIDEODECODERFACTORY_H_ */
