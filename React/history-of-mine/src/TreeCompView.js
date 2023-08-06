import * as React from "react";
import {useEffect, useRef, useState} from "react";
import Tree from "./Tree";
import AvatarTreeItem from "./AvatarTreeItem";
import "./TreeCompView.css"
import axios from "axios";


const USER_AVATAR = 130;
const RELATIVE_AVATAR = 100;


function calculatePosition(ref, prevStateLength, as, sex){
    const maxWidth = ref.current.getBoundingClientRect().width;
    const maxHeight = ref.current.getBoundingClientRect().height;

    let top;
    let left;

    switch (as){
        case "SIBLING":{
            top = maxHeight - USER_AVATAR - 30;
            left = maxWidth / 2 - RELATIVE_AVATAR / 2 - (prevStateLength * RELATIVE_AVATAR);
            break;
        }
        case "PARENT": {
             top =  maxHeight - RELATIVE_AVATAR - 100 - (RELATIVE_AVATAR + 200);
             left =  maxWidth / 2 - USER_AVATAR / 2
             left += sex === "MALE" ? 200 : -200;
            break;
        }
        default:{
            top = maxHeight - USER_AVATAR - 30;
            left = maxWidth / 2 - USER_AVATAR / 2;
        }



    }
    return {
        top: top,
        left: left
    };
}



export default function TreeCompView(){
    const[avList, setAvList] = useState([]);
    const ref = useRef(null);


    useEffect((e) => {
        axios({
            method: "get",
            url: "/user/relative",
            headers: {"Content-Type": "application/json"},

        }).then((response) => {
            if(response.data != null) {
                console.log(response.data)
                addAvatar(e, 302, 1, "SELF", "MALE", "Wojtek");
                const arr = response.data
                arr.forEach((obj) => addAvatar(e, obj.id, obj.relatedTo, obj.as, obj.sex, obj.name))
            }
        })
    },[]);



    const addAvatar = (e, id, relatedTo, as, sex, name) => {
        setAvList((prevState) => {
            const prevStateLength = relatedTo == null ? 0 : prevState.length + 1;
            return [...prevState,
            <AvatarTreeItem
                key={prevState.length + 1}
                alt={name}
                position={calculatePosition(ref, prevStateLength, as, sex)}
                treeId={relatedTo}
                connectId={id}
                as={as}
                containerRef={ref}
            ></AvatarTreeItem>]});
    };

    return(
         <div className="container">
             <div className="containerAvatar" ref={ref} onContextMenu={(e) => e.preventDefault()} >
                 <Tree containerRef={ref}>
                     {avList}
                 </Tree>
             </div>
         </div>
    );
}

