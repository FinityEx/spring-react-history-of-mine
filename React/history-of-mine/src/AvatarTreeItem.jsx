import {forwardRef, useImperativeHandle, useRef} from 'react';
import {Avatar} from '@mui/material';
import {deepOrange} from "@mui/material/colors";

const AvatarTreeItem = forwardRef(({ treeId, connectId, as, alt, src, onClick, position}, ref) => {
    const divRef = useRef(null);

    let divStyle = {
        position: 'absolute',
        left: position.left,
        top: position.top,
        zIndex: 10000
    };

    useImperativeHandle(ref, () => ({
        ...divRef
    }));

    return (
        <div
            ref={divRef}
            data-tree-id={treeId}
            data-tree-connect-from={connectId}
            as={as}
            style={divStyle}
            onClick={(e) => onClick(e, treeId, alt)}
        >
            <Avatar
                    alt={alt}
                    src={src}
                    sx={{ width: 100,
                        height: 100,
                        bgcolor:  deepOrange[500]}}
            >{alt}</Avatar>
        </div>
    );
});


function stringAvatar(name) {
    return `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`
}


export default AvatarTreeItem;