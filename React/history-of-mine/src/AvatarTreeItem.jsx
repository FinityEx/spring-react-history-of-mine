import * as React from 'react';
import {forwardRef, useImperativeHandle, useRef, useState} from 'react';
import {Avatar, ButtonGroup} from '@mui/material';
import {deepOrange} from "@mui/material/colors";
import Popper from "@mui/material/Popper";
import Fade from '@mui/material/Fade';
import Button from "@mui/material/Button";
import RelativeForm from "./RelativeForm"


const AvatarTreeItem = forwardRef(({ treeId, connectId, as, alt, src, position}, ref) => {
    const divRef = useRef(null);
    const [anchorEl, setAnchorEl] = useState(null);
    const [anchorElB, setAnchorElB] = useState(null);
    const openPopper = Boolean(anchorEl);
    const openPopperB = Boolean(anchorElB)

    let divStyle = {
        position: 'fixed',
        left: position.left,
        top: position.top,
    };

    useImperativeHandle(ref, () => ({
        ...divRef
    }));


    const handleAnchorClick = (e) => {
        setAnchorEl(e.currentTarget);
        {console.log("dsjfjdsiofj")}

    }

    const handleAnchorBClick = (e) => {
        setAnchorEl(null)
        setAnchorElB(e.currentTarget)

    }
    const handleClickAway = () => {
        if(anchorElB){
            setAnchorElB(null)
        }
        else {
            setAnchorEl(null)
        }
    }

    return (
        <div
            ref={divRef}
            data-tree-id={treeId}
            data-tree-connect-from={connectId}
            as={as}
            style={divStyle}
            onClick={handleAnchorClick}
        >
            {/*<ClickAwayListener onClickAway={handleClickAway}>*/}
        <div>
                <Avatar

                    alt={alt}
                    src={src}
                    sx={{ width: 100,
                        height: 100,
                        bgcolor:  deepOrange[500]}}
                >{alt}</Avatar>
                <Popper
                    id={openPopper ? 'transition-popper' : undefined}
                    ref={divRef}
                    placement={'top'}
                    open={openPopper}
                    anchorEl={anchorEl}
                    transition>
                    {({ TransitionProps }) => (
                        <Fade {...TransitionProps} timeout={350}>
                            <div>{
                                <ButtonGroup ref={divRef} variant="contained" aria-label="outlined primary button group">
                                    <Button >Show info card</Button>
                                    <Button onClick={handleAnchorBClick}>Add new relative</Button>
                                </ButtonGroup>
                            }

                            </div>
                        </Fade>
                    )}
                </Popper>
                <Popper
                    id={openPopperB ? 'simple-popper' : undefined}
                    ref={divRef}
                    placement={'top'}
                    open={openPopperB}
                    anchorEl={anchorElB}
                    transition>
                    {({ TransitionProps }) => (
                        <Fade {...TransitionProps} timeout={350}>
                            <div>{
                                <RelativeForm/>
                            }
                            </div>
                        </Fade>
                    )}
                </Popper>
            {/*</ClickAwayListener>*/}
        </div>
        </div>
);
});


function stringAvatar(name) {
    return `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`
}


export default AvatarTreeItem;