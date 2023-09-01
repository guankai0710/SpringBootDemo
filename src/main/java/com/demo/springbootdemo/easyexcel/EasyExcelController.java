//package com.demo.springbootdemo.easyexcel;
//
//import com.demo.springbootdemo.vo.Result;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * 描述：TODO
// *
// * @author guankai
// * @date 2022/12/14
// **/
//@RestController
//@RequestMapping("/excel")
//public class EasyExcelController {
//
//
//    /**
//     * 上传数据
//     *
//     * @param file 支持.xlsx
//     * @return 提示消息
//     */
//    @PostMapping("/import")
//    public Result importDedicatedLine(MultipartFile file) {
//        return Result.success(importDedicatedLineService.importDedicatedLine(file));
//    }
//
//}
