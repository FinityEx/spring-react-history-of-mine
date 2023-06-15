// TODO remove, this demo shouldn't need to reset the theme.
import {createTheme, ThemeProvider} from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import Container from "@mui/material/Container";
import Footer from "./home/Footer";
import * as React from "react";
import axios from "axios";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";


// TODO remove, this demo shouldn't need to reset the theme.
const defaultTheme = createTheme();

export default function SeeRelatives() {

    const handleRequest = (event) => {
        event.preventDefault();
        axios({
            method: "get",
            url: "/user/relative",
            headers: {"Content-Type": "application/json"},
        }).then((response) => {
            console.log(response);
        })
    }

    return (
        <ThemeProvider theme={defaultTheme}>
            <CssBaseline />
            <Container maxWidth="lg" >
                <Box component="form" noValidate sx={{ mt: 1 }} onSubmit={handleRequest}>

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                        onClick={handleRequest}
                    >
                        Sign In
                    </Button>
            </Box>
            </Container>
            <Footer
                title="Footer"
                description="Something here to give the footer a purpose!"
            />
        </ThemeProvider>
    );
}
