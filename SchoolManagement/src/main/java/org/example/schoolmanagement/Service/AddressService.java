package org.example.schoolmanagement.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.Api.ApiException;
import org.example.schoolmanagement.DTO.AddressDTO;
import org.example.schoolmanagement.Model.Address;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Repository.AddressRepository;
import org.example.schoolmanagement.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private final TeacherRepository teacherRepository;

    public List<Address> findAllAddresses(){
        List<Address> addresses = addressRepository.findAll();
        if (addresses.isEmpty()){
            throw new ApiException("addresses not found");
        }
        return addresses;
    }

    public void assignAddressToTeacher(AddressDTO addressDTO){
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacherId());
        if (teacher == null){
            throw new ApiException("teacher not found");
        }
        teacher.setAddress(new Address(null,addressDTO.getArea(),addressDTO.getStreet(),addressDTO.getBuildingNumber(),teacher));
        teacherRepository.save(teacher);
    }

    public void updateAddress(Integer addressId, AddressDTO addressDTO){
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacherId());
        if (teacher == null){
            throw new ApiException("teacher not found");
        }
        teacher.getAddress().setArea(addressDTO.getArea());
        teacher.getAddress().setStreet(addressDTO.getStreet());
        teacher.getAddress().setBuildingNumber(addressDTO.getBuildingNumber());

        teacherRepository.save(teacher);
    }


    public void deleteAddress(Integer addressId){
        Address address = addressRepository.findAddressById(addressId);
        if (address == null){
            throw new ApiException("address not found");
        }

        Teacher teacher = teacherRepository.findTeacherByAddress(address);
        if (teacher == null){
            throw new ApiException("teacher not found");
        }
        teacher.setAddress(null);
        teacherRepository.save(teacher);
        addressRepository.delete(address);
    }
}
