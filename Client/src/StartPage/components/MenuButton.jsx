import React, {useState} from 'react';
import './MenuButton.css'



function MenuButton(props){

    const buttonStyle = {
        display: props.display,
        left: props.left + 'px',
        right: props.right + 'px',
        bottom: props.bottom + 'px',
        top: props.top + 'px'
      };

    return(
        <button className="menu-button" id={props.id} onClick={props.onClick}
                style={buttonStyle}>
            {props.content}
        </button>
    )
}

export default MenuButton;