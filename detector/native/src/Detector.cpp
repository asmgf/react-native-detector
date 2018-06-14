#include "Detector.hpp"

namespace asmgf {
    namespace detector {
        bool Detector::detect(cv::Mat& rgbaMat) {
            static const int threshold = 15;

            cv::Mat grayMat;
            cv::cvtColor(rgbaMat, grayMat, CV_RGBA2GRAY);

            std::vector<cv::Point> corners;
            cv::goodFeaturesToTrack(grayMat, corners, threshold, 0.01, 10);

            return corners.size() >= threshold;
        }
    }
}
