     //缩放图片
     function resizeImg(width,height,imgId){
       var img = $("#"+imgId);
       var _width = img.prop("width");
       var _height = img.prop("height");
       
       _height = _height * (width / _width);
       _width = width;//以宽为准进行变化
       
       if(_height > height){
        //以高为准再次变化
       _width = _width * (height / _height);
       _height = height;
       } 
       
       img.prop("width",_width);
       img.prop("height",_height);
       img.show();
     }