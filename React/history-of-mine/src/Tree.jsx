import React, {createRef, useEffect, useState} from 'react';
import Arrow from './component/Arrow';
import useMutationObserver from './hooks/useMutationObserver';

const Tree = ({ children, containerRef }) => {
  const refs = children.map(() => createRef());
  const [arrows, setArrows] = useState([]);

  useMutationObserver(containerRef, () => {
    clearArrows();
    drawArrows();
  });

  useEffect(() => {
    clearArrows();
    drawArrows();
  }, [children]);

  const clearArrows = () => {
    setArrows([]);
  };

  const drawArrows = () => {
    const containerBox = containerRef.current.getBoundingClientRect();
    refs.forEach((ref) => {
      const drawArrow = refs.filter(
        (ref2) =>
          ref.current.current.getAttribute('data-tree-id') ===
          ref2.current.current.getAttribute('data-tree-connect-from')
      );

      drawArrow.forEach((ref3) => {
        const drawArrowhead = ref.current.current.getAttribute('as') !== "SIBLING";
        // get bounding boxes of the two elements
        const box1 = ref.current.current.getBoundingClientRect();
        const box2 = ref3.current.current.getBoundingClientRect();

        // calculate mid points of the bounding boxes relative to the container
        const midX1 = box1.left - containerBox.left + box1.width / 2;
        const midY1 = box1.top - containerBox.top + box1.height / 2;
        const midX2 = box2.left - containerBox.left + box2.width / 2;
        const midY2 = box2.top - containerBox.top + box2.height / 2;

        // calculate the direction vector from box1 to box2
        const dirX = midX2 - midX1;
        const dirY = midY2 - midY1;

        // normalize the direction vector
        const dirLength = Math.sqrt(dirX * dirX + dirY * dirY);
        const dirNormX = dirX / dirLength;
        const dirNormY = dirY / dirLength;

        // calculate the start and end points of the arrow line on the edges of the boxes
        const startX = midX1 + (dirNormX * box1.width) / 2;
        const startY = midY1 + (dirNormY * box1.height) / 2;
        const endX = midX2 - (dirNormX * box2.width) / 2;
        const endY = midY2 - (dirNormY * box2.height) / 2;

        setArrows((prevState) => [
          ...prevState,
          <Arrow key={prevState.length + 1} x1={startX} y1={startY} x2={endX} y2={endY} arrowhead={drawArrowhead}/>
        ]);
      });
    });
  };

  return (
    <>
      {children.map((child, i) => React.cloneElement(child, { ref: refs[i] }))}
      {arrows}
    </>
  );
};

export default Tree;
