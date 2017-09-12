/*
 * VideoDecoder_H264.cpp
 *
 *  Created on: 2016-10-5
 *      Author: yangzhihui
 */
#include "include/VideoDecoder.h"
#include "include/logger.h"
#include <string.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

extern "C"
{
#include "include/ffmpeg/libavcodec/avcodec.h"
#include "include/ffmpeg/libavutil/frame.h"
}

static FILE * decoderframe = NULL;

static int initialize(struct _VideoDecoder *pDecoder, void *param)
{
    /*    ISVCDecoder * _decoder = NULL;
     _decoder = (ISVCDecoder *) pDecoder->pDecoderInstance;
     if (_decoder == NULL)
     {
     return false;
     }
     SDecodingParam decParam;
     memset(&decParam, 0, sizeof(SDecodingParam));
     decParam.sVideoProperty.size = sizeof(decParam.sVideoProperty);
     decParam.uiTargetDqLayer = (uint8_t) - 1;
     decParam.eEcActiveIdc = ERROR_CON_FRAME_COPY_CROSS_IDR;
     decParam.sVideoProperty.eVideoBsType = VIDEO_BITSTREAM_AVC;
     int32_t iErrorConMethod =
     (int32_t) ERROR_CON_SLICE_MV_COPY_CROSS_IDR_FREEZE_RES_CHANGE;
     _decoder->SetOption(DECODER_OPTION_ERROR_CON_IDC, &iErrorConMethod);
     long rv = _decoder->Initialize(&decParam);
     if (0 != rv)
     {
     return -1;
     }
     return (int32_t) rv;*/

    /* open it */
    if (avcodec_open2((AVCodecContext *) pDecoder->pDecoderContext,
            (AVCodec *) pDecoder->pDecoderInstance, NULL) < 0)
    {
        LOG_ERROR("Could not open video codec\n");
        return false;
    }
}

static int decodeframe(struct _VideoDecoder *pDecoder,
        const unsigned char * pSrc,
        const int iSrcLen, void (*successFunc)(Lisp_AVFrame *poutput))
{
    LOG_DEBUG(" threadId = %d, decodeframe processing pSrc=%p iSrcLen = %d ",
            gettid(),
            pSrc, iSrcLen);
    if (NULL == pDecoder)
    {
        return -1;
    }
    AVCodecContext * context = (AVCodecContext *) pDecoder->pDecoderContext;
    if (NULL == context)
    {
        return -2;
    }
    
    AVFrame *frame;
    frame = av_frame_alloc();
    if (NULL == frame)
    {
        return -3;
    }
    int ret = 0;
    return ret;
    //ret = avcodec_send_packet(context, );
    /*    if (pDecoder == NULL)
     {
     // (*failureFunc)(-1);
     return -1;
     }
     if (pDecoder->pDecoderInstance == NULL)
     {
     //(*failureFunc)(-2);
     return -2;
     }
     ISVCDecoder * _decoder = (ISVCDecoder *) pDecoder->pDecoderInstance;
     if (pSrc == NULL)
     {
     //(*failureFunc)(-3);
     return -3;
     }
     if (iSrcLen <= 0)
     {
     //(*failureFunc)(-4);
     return -4;
     }
     
     uint8_t* data[3];
     SBufferInfo bufInfo;
     memset(data, 0, sizeof(data));
     memset(&bufInfo, 0, sizeof(SBufferInfo));
     
     DECODING_STATE rv = _decoder->DecodeFrame2(pSrc, iSrcLen,
     (unsigned char**) data,
     &bufInfo);
     
     if (rv != dsErrorFree)
     {
     MY_LOG_ERROR("decode failed");
     //(*failureFunc)(-6);
     return -6;
     }
     if (bufInfo.iBufferStatus == 1)
     {
     int size_y = bufInfo.UsrData.sSystemBuffer.iStride[0]
     * bufInfo.UsrData.sSystemBuffer.iHeight;
     int size_u = bufInfo.UsrData.sSystemBuffer.iStride[1]
     * (bufInfo.UsrData.sSystemBuffer.iHeight / 2);
     int size_v = bufInfo.UsrData.sSystemBuffer.iStride[1]
     * (bufInfo.UsrData.sSystemBuffer.iHeight / 2);
     
     AVFrame * frame = createFrame(size_y, data[0], size_u,
     data[1], size_v, data[2], bufInfo.UsrData.sSystemBuffer.iWidth,
     bufInfo.UsrData.sSystemBuffer.iHeight,
     bufInfo.UsrData.sSystemBuffer.iStride[0],
     bufInfo.UsrData.sSystemBuffer.iStride[1],
     bufInfo.UsrData.sSystemBuffer.iStride[2]);
     successFunc(frame);
     return 0;
     }
     return -7;*/
}

static void decodeslice(struct _VideoDecoder *pDecoder, const uint8_t * pSrc,
        const int iSrcLen, void (*successFunc)(Lisp_AVFrame * poutput),
        void (*failureFunc)(int errorNo))
{
}

static void destoryvideocoder(struct _VideoDecoder *pDecoder)
{
    /*    if (pDecoder != NULL && pDecoder->pDecoderInstance != NULL)
     {
     ISVCDecoder * _decoder = (ISVCDecoder *) pDecoder->pDecoderInstance;
     _decoder->Uninitialize();
     WelsDestroyDecoder(_decoder);
     pDecoder->pDecoderInstance = NULL;
     }*/
}
Video_Decoder_ptr createVideoDecoderH264()
{
    /*    Video_Decoder_ptr h264decoder = NULL;

     ISVCDecoder * _decoder = NULL;
     h264decoder = (Video_Decoder_ptr) malloc(sizeof(Video_Decoder));
     memset(h264decoder, 0, sizeof(Video_Decoder));
     if (h264decoder != NULL)
     {

     h264decoder->initialize = initialize;
     h264decoder->decodeframe = decodeframe;
     h264decoder->decodeslice = decodeslice;
     h264decoder->destoryvideocoder = destoryvideocoder;
     long rv = WelsCreateDecoder(&_decoder);
     if (_decoder == NULL)
     {
     return NULL;
     }
     h264decoder->pDecoderInstance = _decoder;
     }
     return h264decoder;*/
    Video_Decoder_ptr h264decoder = NULL;
    
    h264decoder = (Video_Decoder_ptr) malloc(sizeof(Video_Decoder));
    memset(h264decoder, 0, sizeof(Video_Decoder));
    if (h264decoder != NULL)
    {
        
        h264decoder->initialize = initialize;
        h264decoder->decodeframe = decodeframe;
        h264decoder->decodeslice = decodeslice;
        h264decoder->destoryvideocoder = destoryvideocoder;
        
        /* Find the H264 video decoder*/
        AVCodec *decoder = NULL;
        decoder = avcodec_find_decoder(AV_CODEC_ID_H264);
        if (NULL == decoder)
        {
            LOG_ERROR("Codec not found\n");
            free(h264decoder);
            return NULL;
        }
        h264decoder->pDecoderInstance = decoder;
        
        AVCodecContext *context = NULL;
        /* create codec context*/
        context = avcodec_alloc_context3(decoder);
        if (NULL == context)
        {
            LOG_ERROR("Could not allocate video codec context\n");
            free(h264decoder);
            return NULL;
        }
        h264decoder->pDecoderContext = context;
    }
    return h264decoder;
}
