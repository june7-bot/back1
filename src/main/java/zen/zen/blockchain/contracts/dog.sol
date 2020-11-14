// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.8.0;

contract Dog {
    struct Student {
        string name;
        string photo; 
    }

    mapping(uint256 => Student ) dogInfo;

    function setStudentInfo ( uint _dogId, string  memory _name, string  memory _photo) public{
        Student storage student = dogInfo[_dogId];
        
        student.name = _name;
        student.photo = _photo;
        
    }

    function getStudentInfo(uint256 _dogId) public view returns (string  memory name , string  memory photo  ){
        return( dogInfo[_dogId].name,  dogInfo[_dogId].photo);
    }
}