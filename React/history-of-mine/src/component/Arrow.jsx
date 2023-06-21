import React, {useEffect, useRef} from 'react';

const Arrow = ({ x1, y1, x2, y2, arrowhead }) => {
  const canvasRef = useRef(null);

  useEffect(() => {
    const canvas = canvasRef.current;
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    const ctx = canvas.getContext('2d');

    // clear canvas
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // draw the line
    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(x2, y2);
    ctx.stroke();
    if(arrowhead){
      let dx = x2 - x1;
      let dy = y2 - y1;
      let angle = Math.atan2(dy, dx);
      drawArrowhead(ctx, x2, y2, angle - Math.PI / 2);
    }
    // draw the arrowhead at (x2, y2)

  }, [x1, y1, x2, y2]);

  const drawArrowhead = (ctx, x, y, angle) => {
    let length = 5; // Długość grotu
    ctx.save();
    ctx.beginPath();
    ctx.translate(x, y);
    ctx.rotate(angle);
    ctx.moveTo(0, 0);
    ctx.lineTo(-length, -length);
    ctx.lineTo(length, -length);
    ctx.closePath();
    ctx.restore();
    ctx.fill();
  };

  return <canvas ref={canvasRef} style={{ position: 'absolute' }} />;
};

export default Arrow;
