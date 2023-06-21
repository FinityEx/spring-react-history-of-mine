import {forwardRef, useEffect, useImperativeHandle, useRef, useState} from 'react';
import {Avatar} from '@mui/material';
import {CIRCLE_SIZE} from '../../App';

const AvatarTreeMouseMoved = forwardRef(
    ({ treeId, connectId, alt, src, onClick, position }, ref) => {
        const divRef = useRef(null);
        const [pos, setPos] = useState(position);
        const [isDragging, setIsDragging] = useState(false);

        useEffect(() => {
            if (isDragging) {
                document.addEventListener('mousemove', onMouseMove);
            } else {
                document.removeEventListener('mousemove', onMouseMove);
            }

            return () => document.removeEventListener('mousemove', onMouseMove);
        }, [isDragging]);

        let divStyle = {
            position: 'absolute',
            left: pos.left + 'px',
            top: pos.top + 'px',
            zIndex: 10000
        };

        useImperativeHandle(ref, () => ({
            ...divRef
        }));

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
            setPos({
                left: e.clientX - CIRCLE_SIZE / 2,
                top: e.clientY - CIRCLE_SIZE / 2
            });
        };

        return (
            <div
                ref={divRef}
                data-tree-id={treeId}
                data-tree-connect-from={connectId}
                style={divStyle}
                onMouseDown={setMouseDragging}
                onMouseUp={setMouseDragging}
                onClick={(e) => onClick(e, treeId)}
            >
                <Avatar alt={alt} src={src} sx={{ width: CIRCLE_SIZE, height: CIRCLE_SIZE }} />
            </div>
        );
    }
);

export default AvatarTreeMouseMoved;