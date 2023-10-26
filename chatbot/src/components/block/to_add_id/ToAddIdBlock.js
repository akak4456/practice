import React, { useEffect, useRef, useState, memo } from 'react';
import styles from './ToAddIdBlock.module.css';

const ToAddIdBlock = memo(({ block }) => {
  return (
    <div id={block.id} className={styles.block}>
      <p>어떤 id 추가할꺼얌?</p>
    </div>
  );
});

export default ToAddIdBlock;
