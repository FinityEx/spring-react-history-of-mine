import Avatar from './Avatar';
import {useRef, useState} from "react";
import Tree from "./Tree";

export default function TreeCompView(){
        const[avList, setAvList] = useState([]);
        const ref = useRef(null);

        const addAvatar = (e, parentIndex = null) => {
            setAvList((prevState) => [...prevState,
                <Avatar
                key={prevState.length + 1}
                treeId={prevState.length + 1}
                connectId={parentIndex}
                containerRef={ref}
                onClick={addAvatar}
                />]);
        };

    return(

            <div className="container" ref={ref}>
                <button onClick={addAvatar}/>
                <div className="containerAvatar" ref={ref} onContextMenu={(e) => e.preventDefault()}>
                    <Tree containerRef={ref}>

                        {avList}
                        </Tree>
                </div>
                </div>

    );
}
