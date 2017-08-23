function drawPoint(opts){
    document.write("<span id='"+opts.point[0]+""+opts.point[1]+"' style='display: inline-block; width: "+opts.pw+"px; height: "+opts.ph+"px; background-color: "+opts.color+"; position: absolute; left: "+opts.point[0]+"px; top: "+opts.point[1]+"px;'>"+(opts.point[2]?("<div style='position: relative;'><span style='position: absolute; left: 5px; bottom: 1px; text-align: left; width: 100px;'>"+opts.point[2]+"</span></div>"):"")+"</span>");
}

function drawPoint2(opts){
    document.write("<span id='"+"point"+"' style='display: inline-block; width: "+opts.pw+"px; height: "+opts.ph+"px; background-color: "+opts.color+"; position: absolute "+opts.point[0]+"px; top: "+opts.point[1]+"px;'>"+"</span>");
}