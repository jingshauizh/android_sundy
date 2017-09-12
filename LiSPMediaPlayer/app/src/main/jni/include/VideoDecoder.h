/*
 * VideoDecoder.h
 *
 *  Created on: 2016-10-5
 *      Author: yangzhihui
 */

#ifndef VIDEODECODER_H_
#define VIDEODECODER_H_

#include <stdint.h>
#include "AVFrame.h"
#include "codectypedef.h"

#ifdef DEBUG_MEM
#include "include/mymalloc.h"
#else
#include <malloc.h>
#endif

#ifdef __cpluscplus
extern "C"
{   
#endif

typedef struct _VideoDecoder
{
    void * pDecoderInstance;
    void * pDecoderContext;

    int (*initialize)(struct _VideoDecoder *, void * parameter);

    int (*decodeframe)(struct _VideoDecoder *pDecoder,
            const unsigned char * pSrc,
            const int iSrcLen, void (successFunc)(Lisp_AVFrame * frame));

    void (*decodeslice)(struct _VideoDecoder *, const uint8_t * pSrc,
            const int iSrcLen, void (*successFunc)(Lisp_AVFrame * poutput),
            void (*failureFunc)(int errorNo));

    void (*destoryvideocoder)(struct _VideoDecoder *);
    
} Video_Decoder, *Video_Decoder_ptr;

Video_Decoder_ptr createVideoDecoderH264();
static inline void destoryVideoDecoder(Video_Decoder_ptr * ppdecoder)
{
    Video_Decoder_ptr pdecoder = *ppdecoder;
    if (pdecoder != NULL)
    {
        pdecoder->destoryvideocoder(pdecoder);
        free(pdecoder);
    }
    pdecoder = NULL;
}
#ifdef __cpluscplus
}
#endif
#endif /* VIDEODECODERBASE_H_ */
