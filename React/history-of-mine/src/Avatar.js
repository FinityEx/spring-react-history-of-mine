import * as React from 'react'
import {forwardRef, useImperativeHandle, useRef, useState} from 'react'
import styled from 'styled-components';

const AVATAR_SIZE = 200;

const Component = styled.div.attrs((props) => ({
    style: {
        top: `${props.top}px`,
        left: `${props.left}px`
    }
}))`
  position: absolute;
  width: ${AVATAR_SIZE}px;
  height: ${AVATAR_SIZE}px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  cursor: pointer;
  user-select: none;
  z-index: 10000;
`;

const Avatar = forwardRef(({treeId, connectId, onClick, containerRef}, ref) => {
    const divRef = useRef(null);
    const [position, setPosition] = useState(generateRandomPosition());

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

        const randomTop = Math.floor(Math.random() * (maxHeight - AVATAR_SIZE));
        const randomLeft = Math.floor(Math.random() * (maxWidth - AVATAR_SIZE));

        return {
            top: randomTop,
            left: randomLeft
        };
    }

    return (<Avatar
            ref={divRef}
            data-tree-id={treeId}
            data-tree-connect-from={connectId}
            top={position.top}
            left={position.left}
            onClick={(e) => onClick(e, treeId)}
        >
            data-tree-id="{treeId}"<br />
            data-tree-connect-from="{connectId}"
        </Avatar>
    );
});

export default Avatar;