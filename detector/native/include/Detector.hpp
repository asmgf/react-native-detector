#pragma once

#include <opencv2/opencv.hpp>

namespace asmgf {
    namespace detector {
        class Detector {
        public:
            bool detect(cv::Mat& rgbaMat);
        };
    }
}
