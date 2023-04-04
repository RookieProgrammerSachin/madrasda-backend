# DROP PROCEDURE VENDOR_SALES;
CREATE PROCEDURE VENDOR_SALES(IN vendor_id BIGINT)
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

    SELECT total_products, total_orders, total_profit;
END

create
definer = root@`%` procedure TOP_SELLERS_FOR_VENDOR(IN vendor_id bigint)
BEGIN
SELECT
    p.id,
    SUM(tp.quantity) AS total_sales
FROM
    product p
        INNER JOIN transaction_product tp ON tp.product_id = p.id
WHERE
        p.publish_status = 1
  AND p.vendor_id = 3
GROUP BY
    p.id
ORDER BY
    total_sales DESC
LIMIT 10;

END;

