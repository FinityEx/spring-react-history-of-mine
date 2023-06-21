import * as React from 'react';
import {useEffect} from 'react';
import axios from "axios";

export default function DataFetcher(){
    useEffect((e) => {
        handleRequest(e)
    })

    const handleRequest = (event) => {
        event.preventDefault();
        axios({
            method: "get",
            url: "/user/relative",
            headers: {"Content-Type": "application/json"},
        }).then((response) => {
            ;
        })
    }

}