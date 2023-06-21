import styled from 'styled-components';
import {forwardRef, useCallback, useEffect, useImperativeHandle, useRef, useState} from 'react';

const CIRCLE_SIZE = 200;

const Component = styled.div.attrs((props) => ({
    style: {
        backgroundColor: props.bgc,
        top: `${props.top}px`,
        left: `${props.left}px`
    }
}))`
  position: absolute;
  width: ${CIRCLE_SIZE}px;
  height: ${CIRCLE_SIZE}px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  cursor: pointer;
  user-select: none;
  z-index: 10000;
`;

const Circle = forwardRef(({ treeId, connectId, onClick, containerRef }, ref) => {
    const divRef = useRef(null);
    const color = useCallback(generateRandomColor(), []);
    const [position, setPosition] = useState(generateRandomPosition());
    const [isDragging, setIsDragging] = useState(false);

    useEffect(() => {
        if (isDragging) {
            document.addEventListener('mousemove', onMouseMove);
        } else {
            document.removeEventListener('mousemove', onMouseMove);
        }

        return () => document.removeEventListener('mousemove', onMouseMove);
    }, [isDragging]);

    useImperativeHandle(ref, () => ({
        ...divRef
    }));

    function generateRandomColor() {
        const red = Math.floor(Math.random() * 256);
        const green = Math.floor(Math.random() * 256);
        const blue = Math.floor(Math.random() * 256);
        const hexRed = red.toString(16).padStart(2, '0');
        const hexGreen = green.toString(16).padStart(2, '0');
        const hexBlue = blue.toString(16).padStart(2, '0');
        return `#${hexRed}${hexGreen}${hexBlue}`;
    }

    function generateRandomPosition() {
        const maxWidth = containerRef.current.offsetWidth;
        const maxHeight = containerRef.current.offsetHeight;

        const randomTop = Math.floor(Math.random() * (maxHeight - CIRCLE_SIZE));
        const randomLeft = Math.floor(Math.random() * (maxWidth - CIRCLE_SIZE));

        return {
            top: randomTop,
            left: randomLeft
        };
    }

    const setMouseDragging = (e) => {
        if (e.button === 2) {
            if (e.type === 'mousedown') {
                setIsDragging(true);
            } else {
                setIsDragging(false);
            }
        }
    };

    const onMouseMove = (e) => {
        setPosition({
            left: e.clientX - CIRCLE_SIZE / 2,
            top: e.clientY - CIRCLE_SIZE / 2
        });
    };

    return (
        <Component
            ref={divRef}
            bgc={color}
            data-tree-id={treeId}
            data-tree-connect-from={connectId}
            top={position.top}
            left={position.left}
            onMouseDown={setMouseDragging}
            onMouseUp={setMouseDragging}
            onClick={(e) => onClick(e, treeId)}
        >
            data-tree-id="{treeId}"<br />
            data-tree-connect-from="{connectId}"
        </Component>
    );
});

export default Circle;