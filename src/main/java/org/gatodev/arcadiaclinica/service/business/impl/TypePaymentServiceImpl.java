package org.gatodev.arcadiaclinica.service.business.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.business.TypePayment;
import org.gatodev.arcadiaclinica.repository.business.ITypePaymentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Qualifier("typePayment")
public class TypePaymentServiceImpl implements ICategoryService<TypePayment> {

    private final ITypePaymentRepository typePaymentRepository;

    public TypePaymentServiceImpl(ITypePaymentRepository typePaymentRepository) {
        this.typePaymentRepository = typePaymentRepository;
    }

    @Override
    public TypePayment addCategory(TypePayment category) {
        if (typePaymentRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category name already exists");
        }
        return typePaymentRepository.save(category);
    }

    @Override
    public TypePayment updateCategory(TypePayment category) {
        if (!typePaymentRepository.existsById(category.getId())) {
            throw new EntityNotFoundException("Category does not exist");
        }
        category.setState(true);
        return typePaymentRepository.save(category);
    }

    @Override
    public TypePayment getCategoryById(Long id) {
        return typePaymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category does not exist"));
    }

    @Override
    public void deactivateCategoryById(Long id) {
        TypePayment category = getCategoryById(id);
        category.setState(false);
        typePaymentRepository.save(category);
    }

    @Override
    public void activateCategoryById(Long id) {
        TypePayment category = getCategoryById(id);
        category.setState(true);
        typePaymentRepository.save(category);
    }

    @Override
    public List<TypePayment> getAllCategory() {
        return typePaymentRepository.findAll();
    }
}
