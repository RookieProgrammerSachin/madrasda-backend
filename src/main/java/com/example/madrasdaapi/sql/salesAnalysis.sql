
CREATE PROCEDURE vendor_sales(IN vendor_id BIGINT)
BEGIN
    DECLARE total_products INT DEFAULT 0;
    DECLARE total_orders INT DEFAULT 0;
    DECLARE total_profit INT DEFAULT 0;

    SELECT COUNT(*) INTO total_products FROM product WHERE vendor_id = vendor_id;
    SELECT COUNT(*)
    INTO total_orders
    FROM transaction t
             JOIN transaction_vendor tv ON t.id = tv.transaction_id
    WHERE tv.vendor_id = vendor_id;
    SELECT SUM(profit)
    INTO total_profit
    FROM transaction t
             JOIN transaction_vendor tv ON t.id = tv.transaction_id
             JOIN transaction_product tp ON t.id = tp.transaction_id
             JOIN product p ON tp.product_id = p.id
    WHERE p.vendor_id = vendor_id;

    SELECT 'Products Listed' AS `Metric`,
           total_products    AS `Total`
    UNION
    SELECT 'Total Orders',
           total_orders
    UNION
    SELECT 'Total Profit',
           total_profit;

END;
