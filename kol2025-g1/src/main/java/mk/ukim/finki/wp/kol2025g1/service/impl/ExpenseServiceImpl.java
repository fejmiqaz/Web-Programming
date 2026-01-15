package mk.ukim.finki.wp.kol2025g1.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.kol2025g1.model.Expense;
import mk.ukim.finki.wp.kol2025g1.model.ExpenseCategory;
import mk.ukim.finki.wp.kol2025g1.model.exceptions.InvalidExpenseIdException;
import mk.ukim.finki.wp.kol2025g1.model.exceptions.InvalidVendorIdException;
import mk.ukim.finki.wp.kol2025g1.repository.ExpenseRepository;
import mk.ukim.finki.wp.kol2025g1.repository.VendorRepository;
import mk.ukim.finki.wp.kol2025g1.service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static mk.ukim.finki.wp.kol2025g1.service.FieldFilterSpecification.*;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final VendorRepository vendorRepository;

    @Override
    public List<Expense> listAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElseThrow();
    }

    @Override
    public Expense create(String title, LocalDate dateCreated, Double amount, Integer daysToExpire, ExpenseCategory expenseCategory, Long vendorId) {
        return expenseRepository.save(new Expense(title, dateCreated, amount, daysToExpire, expenseCategory, vendorRepository.findById(vendorId).orElseThrow()));
    }

    @Override
    public Expense update(Long id, String title, LocalDate dateCreated, Double amount, Integer daysToExpire, ExpenseCategory expenseCategory, Long vendorId) {
        Expense expense = expenseRepository.findById(id).orElseThrow();
        expense.setTitle(title);
        expense.setDateCreated(dateCreated);
        expense.setAmount(amount);
        expense.setDaysToExpire(daysToExpire);
        expense.setExpenseCategory(expenseCategory);
        expense.setVendor(vendorRepository.findById(vendorId).orElseThrow());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense delete(Long id) {
        expenseRepository.deleteById(id);
        return null;
    }

    @Override
    public Expense extendExpiration(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow();
        expense.setDaysToExpire(expense.getDaysToExpire() + 1);
        return expenseRepository.save(expense);
    }

    @Override
    public Page<Expense> findPage(String title, ExpenseCategory expenseCategory, Long vendor, int pageNum, int pageSize) {
        Specification<Expense> specification = Specification.allOf(
                filterContainsText(Expense.class, "title", title),
                filterEquals(Expense.class, "expenseCategory", expenseCategory),
                filterEquals(Expense.class, "vendor.id", vendor)
        );
        return this.expenseRepository.findAll(
                specification,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "title"))
        );
    }
}
